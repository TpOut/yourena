# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.13

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


# Remove some rules from gmake that .SUFFIXES does not remove.
SUFFIXES =

.SUFFIXES: .hpux_make_needs_suffix_list


# Suppress display of executed commands.
$(VERBOSE).SILENT:


# A target that is always out of date.
cmake_force:

.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /Applications/CLion.app/Contents/bin/cmake/mac/bin/cmake

# The command to remove a file.
RM = /Applications/CLion.app/Contents/bin/cmake/mac/bin/cmake -E remove -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = /Users/wuyue/CLionProjects/c_demo

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /Users/wuyue/CLionProjects/c_demo/cmake-build-debug

# Include any dependencies generated for this target.
include CMakeFiles/leak.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/leak.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/leak.dir/flags.make

CMakeFiles/leak.dir/内存泄漏/leakmem.c.o: CMakeFiles/leak.dir/flags.make
CMakeFiles/leak.dir/内存泄漏/leakmem.c.o: ../内存泄漏/leakmem.c
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/Users/wuyue/CLionProjects/c_demo/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building C object CMakeFiles/leak.dir/内存泄漏/leakmem.c.o"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -o CMakeFiles/leak.dir/内存泄漏/leakmem.c.o   -c /Users/wuyue/CLionProjects/c_demo/内存泄漏/leakmem.c

CMakeFiles/leak.dir/内存泄漏/leakmem.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/leak.dir/内存泄漏/leakmem.c.i"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -E /Users/wuyue/CLionProjects/c_demo/内存泄漏/leakmem.c > CMakeFiles/leak.dir/内存泄漏/leakmem.c.i

CMakeFiles/leak.dir/内存泄漏/leakmem.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/leak.dir/内存泄漏/leakmem.c.s"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -S /Users/wuyue/CLionProjects/c_demo/内存泄漏/leakmem.c -o CMakeFiles/leak.dir/内存泄漏/leakmem.c.s

CMakeFiles/leak.dir/内存泄漏/test.c.o: CMakeFiles/leak.dir/flags.make
CMakeFiles/leak.dir/内存泄漏/test.c.o: ../内存泄漏/test.c
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/Users/wuyue/CLionProjects/c_demo/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Building C object CMakeFiles/leak.dir/内存泄漏/test.c.o"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -o CMakeFiles/leak.dir/内存泄漏/test.c.o   -c /Users/wuyue/CLionProjects/c_demo/内存泄漏/test.c

CMakeFiles/leak.dir/内存泄漏/test.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/leak.dir/内存泄漏/test.c.i"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -E /Users/wuyue/CLionProjects/c_demo/内存泄漏/test.c > CMakeFiles/leak.dir/内存泄漏/test.c.i

CMakeFiles/leak.dir/内存泄漏/test.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/leak.dir/内存泄漏/test.c.s"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -S /Users/wuyue/CLionProjects/c_demo/内存泄漏/test.c -o CMakeFiles/leak.dir/内存泄漏/test.c.s

CMakeFiles/leak.dir/test.c.o: CMakeFiles/leak.dir/flags.make
CMakeFiles/leak.dir/test.c.o: ../test.c
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/Users/wuyue/CLionProjects/c_demo/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_3) "Building C object CMakeFiles/leak.dir/test.c.o"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -o CMakeFiles/leak.dir/test.c.o   -c /Users/wuyue/CLionProjects/c_demo/test.c

CMakeFiles/leak.dir/test.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/leak.dir/test.c.i"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -E /Users/wuyue/CLionProjects/c_demo/test.c > CMakeFiles/leak.dir/test.c.i

CMakeFiles/leak.dir/test.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/leak.dir/test.c.s"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -S /Users/wuyue/CLionProjects/c_demo/test.c -o CMakeFiles/leak.dir/test.c.s

# Object files for target leak
leak_OBJECTS = \
"CMakeFiles/leak.dir/内存泄漏/leakmem.c.o" \
"CMakeFiles/leak.dir/内存泄漏/test.c.o" \
"CMakeFiles/leak.dir/test.c.o"

# External object files for target leak
leak_EXTERNAL_OBJECTS =

leak: CMakeFiles/leak.dir/内存泄漏/leakmem.c.o
leak: CMakeFiles/leak.dir/内存泄漏/test.c.o
leak: CMakeFiles/leak.dir/test.c.o
leak: CMakeFiles/leak.dir/build.make
leak: CMakeFiles/leak.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=/Users/wuyue/CLionProjects/c_demo/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_4) "Linking C executable leak"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/leak.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/leak.dir/build: leak

.PHONY : CMakeFiles/leak.dir/build

CMakeFiles/leak.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/leak.dir/cmake_clean.cmake
.PHONY : CMakeFiles/leak.dir/clean

CMakeFiles/leak.dir/depend:
	cd /Users/wuyue/CLionProjects/c_demo/cmake-build-debug && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /Users/wuyue/CLionProjects/c_demo /Users/wuyue/CLionProjects/c_demo /Users/wuyue/CLionProjects/c_demo/cmake-build-debug /Users/wuyue/CLionProjects/c_demo/cmake-build-debug /Users/wuyue/CLionProjects/c_demo/cmake-build-debug/CMakeFiles/leak.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/leak.dir/depend

