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
include CMakeFiles/second.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/second.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/second.dir/flags.make

CMakeFiles/second.dir/maopao.cpp.o: CMakeFiles/second.dir/flags.make
CMakeFiles/second.dir/maopao.cpp.o: ../maopao.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/Users/wuyue/CLionProjects/c_demo/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/second.dir/maopao.cpp.o"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/second.dir/maopao.cpp.o -c /Users/wuyue/CLionProjects/c_demo/maopao.cpp

CMakeFiles/second.dir/maopao.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/second.dir/maopao.cpp.i"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /Users/wuyue/CLionProjects/c_demo/maopao.cpp > CMakeFiles/second.dir/maopao.cpp.i

CMakeFiles/second.dir/maopao.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/second.dir/maopao.cpp.s"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /Users/wuyue/CLionProjects/c_demo/maopao.cpp -o CMakeFiles/second.dir/maopao.cpp.s

# Object files for target second
second_OBJECTS = \
"CMakeFiles/second.dir/maopao.cpp.o"

# External object files for target second
second_EXTERNAL_OBJECTS =

second: CMakeFiles/second.dir/maopao.cpp.o
second: CMakeFiles/second.dir/build.make
second: CMakeFiles/second.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=/Users/wuyue/CLionProjects/c_demo/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Linking CXX executable second"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/second.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/second.dir/build: second

.PHONY : CMakeFiles/second.dir/build

CMakeFiles/second.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/second.dir/cmake_clean.cmake
.PHONY : CMakeFiles/second.dir/clean

CMakeFiles/second.dir/depend:
	cd /Users/wuyue/CLionProjects/c_demo/cmake-build-debug && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /Users/wuyue/CLionProjects/c_demo /Users/wuyue/CLionProjects/c_demo /Users/wuyue/CLionProjects/c_demo/cmake-build-debug /Users/wuyue/CLionProjects/c_demo/cmake-build-debug /Users/wuyue/CLionProjects/c_demo/cmake-build-debug/CMakeFiles/second.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/second.dir/depend

