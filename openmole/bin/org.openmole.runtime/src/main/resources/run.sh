#!/usr/bin/env bash

#readlink -f does not work on mac, use alternate script
TARGET_FILE="$0"

cd `dirname "$TARGET_FILE"`
TARGET_FILE=`basename "$TARGET_FILE"`

# Iterate down a (possible) chain of symlinks
while [ -L "$TARGET_FILE" ]
do
    TARGET_FILE=`readlink "$TARGET_FILE"`
    cd `dirname "$TARGET_FILE"`
    TARGET_FILE=`basename "$TARGET_FILE"`
done

REALPATH="$TARGET_FILE"
#end of readlink -f

LOCATION=$( cd $(dirname "$REALPATH") ; pwd -P )

MEMORY=$1
shift

TMPDIR=$1
shift
mkdir -p "${TMPDIR}"

FLAG=""

JVMVERSION=`java -version 2>&1 | tail -1 -`

case "$JVMVERSION" in
  *64-Bit*) FLAG="-XX:+UseCompressedOops";;
esac

for a in $@
do
  if [ $a = "--debug" ]; then FLAG="$FLAG -XX:-OmitStackTraceInFastThrow"; fi
done


CONFIGDIR="${TMPDIR}/config"
mkdir -p "${CONFIGDIR}"

ulimit -S -v unlimited
ulimit -S -s unlimited

export MALLOC_ARENA_MAX=1

if [ `locale -a | grep C.UTF-8` ]; then OM_LOCAL="C.UTF-8"
elif [ `locale -a | grep en_US.utf8` ]; then OM_LOCAL="en_US.utf8"
elif [ `locale -a | grep utf8` ]; then OM_LOCAL=`locale -a |  grep utf8 | head -1`
else
  echo "No UTF8 locale found among installed locales"
  locale -a
fi

if [ -n "$OM_LOCAL" ]; then
  export LC_ALL=$OM_LOCAL
  export LANG=$OM_LOCAL
fi

java -Djava.io.tmpdir="${TMPDIR}" -Dsun.jnu.encoding=UTF-8 -Dfile.encoding=UTF-8 -Duser.country=US -Duser.language=en -Xss2M -Xms64m -Xmx${MEMORY} -Dosgi.locking=none -Dosgi.configuration.area="${CONFIGDIR}" $FLAG -XX:ReservedCodeCacheSize=128m -XX:MaxMetaspaceSize=256m -XX:CompressedClassSpaceSize=128m \
  -XX:+UseG1GC -XX:ParallelGCThreads=1 -XX:CICompilerCount=2 -XX:ConcGCThreads=1 -XX:G1ConcRefinementThreads=1 \
  -cp "${LOCATION}/launcher/*" org.openmole.launcher.Launcher --plugins "${LOCATION}/plugins/" --priority "logging" --run org.openmole.runtime.SimExplorer --osgi-directory "${CONFIGDIR}" -- --workspace "${TMPDIR}" $@

RETURNCODE=$?

rm -rf "${TMPDIR}"

exit $RETURNCODE

