#ifndef OBJLOADER_H
#define OBJLOADER_H
#include <vector>
#include <glew.h>

bool loadOBJ(
	const char * path, 
	std::vector<GLfloat> & out_vertices
);

#endif