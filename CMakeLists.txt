cmake_minimum_required(VERSION 3.24)
project(projects)

set(CMAKE_CXX_STANDARD 20)

set(debug_options "-D_GLIBCXX_DEBUG=1 -D_GLIBCXX_DEBUG_PEDANTIC=1 -D_FORTIFY_SOURCE=2")
#add_compile_definitions(DEBUG)

#set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS}")
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} ${debug_options}")

add_executable(projects main.cpp)
