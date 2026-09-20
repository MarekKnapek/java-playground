#!/usr/bin/env bash
set -x
set -e
mk_this_dir=$( cd -- "$( dirname -- "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )
mk_cp_dir="${mk_this_dir}"/../build/classes
java -cp "${mk_cp_dir}" mk.gui.draw
