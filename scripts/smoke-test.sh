#!/usr/bin/env bash
# Compiles the Gameholic server against JavaServerClientAPI's jars and runs the two-client relay smoke test.
# Needs JDK 8+ and a sibling checkout: ../JavaServerClientAPI (same layout the IntelliJ project expects).
set -euo pipefail
cd "$(dirname "$0")/.."
JSC=${JSC_LIBS:-../JavaServerClientAPI/JSC/libs}
CP="$JSC/JConnection.jar:$JSC/JSERVER.jar"
out=$(mktemp -d); trap 'rm -rf "$out"' EXIT
javac -d "$out" -cp "$CP" $(find gameholic/server/src gameholic/server/test -name '*.java')
java -cp "$out:$CP" RelaySmokeTest
