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
CMAKE_SOURCE_DIR = /Users/wuyue/CLionProjects/cplusplus

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /Users/wuyue/CLionProjects/cplusplus/cmake-build-debug

# Include any dependencies generated for this target.
include CMakeFiles/one.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/one.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/one.dir/flags.make

CMakeFiles/one.dir/namespace_demo.cpp.o: CMakeFiles/one.dir/flags.make
CMakeFiles/one.dir/namespace_demo.cpp.o: ../namespace_demo.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/Users/wuyue/CLionProjects/cplusplus/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/one.dir/namespace_demo.cpp.o"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/one.dir/namespace_demo.cpp.o -c /Users/wuyue/CLionProjects/cplusplus/namespace_demo.cpp

CMakeFiles/one.dir/namespace_demo.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/one.dir/namespace_demo.cpp.i"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /Users/wuyue/CLionProjects/cplusplus/namespace_demo.cpp > CMakeFiles/one.dir/namespace_demo.cpp.i

CMakeFiles/one.dir/namespace_demo.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/one.dir/namespace_demo.cpp.s"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /Users/wuyue/CLionProjects/cplusplus/namespace_demo.cpp -o CMakeFiles/one.dir/namespace_demo.cpp.s

# Object files for target one
one_OBJECTS = \
"CMakeFiles/one.dir/namespace_demo.cpp.o"

# External object files for target one
one_EXTERNAL_OBJECTS =

one: CMakeFiles/one.dir/namespace_demo.cpp.o
one: CMakeFiles/one.dir/build.make
one: CMakeFiles/one.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=/Users/wuyue/CLionProjects/cplusplus/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Linking CXX executable one"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/one.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/one.dir/build: one

.PHONY : CMakeFiles/one.dir/build

CMakeFiles/one.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/one.dir/cmake_clean.cmake
.PHONY : CMakeFiles/one.dir/clean

CMakeFiles/one.dir/depend:
	cd /Users/wuyue/CLionProjects/cplusplus/cmake-build-debug && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /Users/wuyue/CLionProjects/cplusplus /Users/wuyue/CLionProjects/cplusplus /Users/wuyue/CLionProjects/cplusplus/cmake-build-debug /Users/wuyue/CLionProjects/cplusplus/cmake-build-debug /Users/wuyue/CLionProjects/cplusplus/cmake-build-debug/CMakeFiles/one.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/one.dir/depend

