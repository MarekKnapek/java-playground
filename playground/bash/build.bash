#!/usr/bin/env bash
set -x
set -e
mk_this_dir=$( cd -- "$( dirname -- "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )
mk_src_dir="${mk_this_dir}"/../src
mk_cp_dir="${mk_src_dir}"
mk_out_dir="${mk_this_dir}"/../build/classes
javac -d "${mk_out_dir}" -cp "${mk_src_dir}" "${mk_src_dir}"/mk/gui/draw.java
javac -d "${mk_out_dir}" -cp "${mk_src_dir}" "${mk_src_dir}"/mk/gui/frame.java
javac -d "${mk_out_dir}" -cp "${mk_src_dir}" "${mk_src_dir}"/mk/gui/gui_starter.java
javac -d "${mk_out_dir}" -cp "${mk_src_dir}" "${mk_src_dir}"/mk/gui/guiapp1.java
javac -d "${mk_out_dir}" -cp "${mk_src_dir}" "${mk_src_dir}"/mk/jvm/info.java
javac -d "${mk_out_dir}" -cp "${mk_src_dir}" "${mk_src_dir}"/mk/test/itest.java
javac -d "${mk_out_dir}" -cp "${mk_src_dir}" "${mk_src_dir}"/mk/test/test.java
javac -d "${mk_out_dir}" -cp "${mk_src_dir}" "${mk_src_dir}"/mk/uint/constants.java
javac -d "${mk_out_dir}" -cp "${mk_src_dir}" "${mk_src_dir}"/mk/uint/u128.java
javac -d "${mk_out_dir}" -cp "${mk_src_dir}" "${mk_src_dir}"/mk/uint/u16.java
javac -d "${mk_out_dir}" -cp "${mk_src_dir}" "${mk_src_dir}"/mk/uint/u256.java
javac -d "${mk_out_dir}" -cp "${mk_src_dir}" "${mk_src_dir}"/mk/uint/u32.java
javac -d "${mk_out_dir}" -cp "${mk_src_dir}" "${mk_src_dir}"/mk/uint/u64.java
javac -d "${mk_out_dir}" -cp "${mk_src_dir}" "${mk_src_dir}"/mk/utils.java
javac -d "${mk_out_dir}" -cp "${mk_src_dir}" "${mk_src_dir}"/test/mk/uint/base.java
javac -d "${mk_out_dir}" -cp "${mk_src_dir}" "${mk_src_dir}"/test/mk/uint/benchmark.java
javac -d "${mk_out_dir}" -cp "${mk_src_dir}" "${mk_src_dir}"/test/mk/uint/common.java
javac -d "${mk_out_dir}" -cp "${mk_src_dir}" "${mk_src_dir}"/test/mk/uint/test_all.java
javac -d "${mk_out_dir}" -cp "${mk_src_dir}" "${mk_src_dir}"/test/mk/uint/test_u128.java
javac -d "${mk_out_dir}" -cp "${mk_src_dir}" "${mk_src_dir}"/test/mk/uint/test_u16.java
javac -d "${mk_out_dir}" -cp "${mk_src_dir}" "${mk_src_dir}"/test/mk/uint/test_u256.java
javac -d "${mk_out_dir}" -cp "${mk_src_dir}" "${mk_src_dir}"/test/mk/uint/test_u32.java
javac -d "${mk_out_dir}" -cp "${mk_src_dir}" "${mk_src_dir}"/test/mk/uint/test_u64.java
