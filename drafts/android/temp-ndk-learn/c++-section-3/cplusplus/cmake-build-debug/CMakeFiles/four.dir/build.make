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
include CMakeFiles/four.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/four.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/four.dir/flags.make

CMakeFiles/four.dir/Function.cpp.o: CMakeFiles/four.dir/flags.make
CMakeFiles/four.dir/Function.cpp.o: ../Function.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/Users/wuyue/CLionProjects/cplusplus/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/four.dir/Function.cpp.o"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/four.dir/Function.cpp.o -c /Users/wuyue/CLionProjects/cplusplus/Function.cpp

CMakeFiles/four.dir/Function.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/four.dir/Function.cpp.i"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /Users/wuyue/CLionProjects/cplusplus/Function.cpp > CMakeFiles/four.dir/Function.cpp.i

CMakeFiles/four.dir/Function.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/four.dir/Function.cpp.s"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /Users/wuyue/CLionProjects/cplusplus/Function.cpp -o CMakeFiles/four.dir/Function.cpp.s

# Object files for target four
four_OBJECTS = \
"CMakeFiles/four.dir/Function.cpp.o"

# External object files for target four
four_EXTERNAL_OBJECTS =

four: CMakeFiles/four.dir/Function.cpp.o
four: CMakeFiles/four.dir/build.make
four: CMakeFiles/four.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=/Users/wuyue/CLionProjects/cplusplus/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Linking CXX executable four"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/four.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/four.dir/build: four

.PHONY : CMakeFiles/four.dir/build

CMakeFiles/four.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/four.dir/cmake_clean.cmake
.PHONY : CMakeFiles/four.dir/clean

CMakeFiles/four.dir/depend:
	cd /Users/wuyue/CLionProjects/cplusplus/cmake-build-debug && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /Users/wuyue/CLionProjects/cplusplus /Users/wuyue/CLionProjects/cplusplus /Users/wuyue/CLionProjects/cplusplus/cmake-build-debug /Users/wuyue/CLionProjects/cplusplus/cmake-build-debug /Users/wuyue/CLionProjects/cplusplus/cmake-build-debug/CMakeFiles/four.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/four.dir/depend

