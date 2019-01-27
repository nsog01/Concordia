#include <vector>
#include <stdio.h>
#include <cstring>

#include <glm.hpp>

#include "objloader.hpp"

// .OBJ Loader from http://www.opengl-tutorial.org/beginners-tutorials/tutorial-7-model-loading/

#pragma warning(disable:4996)

bool loadOBJ(const char * path, std::vector<GLfloat> & out_vertices, std::vector<GLuint> & out_indices)
{
	printf("Loading OBJ file %s...\n", path);

	FILE * file = fopen(path, "r");

	if( file == NULL ){
		printf("Impossible to open the file ! Are you in the right path ?\n");
		getchar();
		return false;
	}

	while( 1 ){

		char lineHeader[128];
		// read the first word of the line
		int res = fscanf(file, "%s", lineHeader);
		if (res == EOF)
			break; // EOF = End Of File. Quit the loop.

		if ( strcmp( lineHeader, "v" ) == 0 ){
			glm::vec3 vertex;

			fscanf(file, "%f %f %f\n", &vertex.x, &vertex.y, &vertex.z );

			out_vertices.push_back(vertex.x);
			out_vertices.push_back(vertex.y);
			out_vertices.push_back(vertex.z);

		}else if ( strcmp( lineHeader, "f" ) == 0 ){

			GLuint idx1, idx2, idx3; //3 indices per line

			int matches = fscanf(file, "%i %i %i \n", &idx1, &idx2, &idx3);

			if (matches != 3){
				printf("File can't be read by our simple parser :-( Try exporting with other options\n");
				return false;
			}

			out_indices.push_back(idx1 - 1); //why -1? hint: look at the first index in teddy.obj
			out_indices.push_back(idx2 - 1);
			out_indices.push_back(idx3 - 1);

		}else{
			// Probably a comment, eat up the rest of the line
			char stupidBuffer[1000];
			fgets(stupidBuffer, 1000, file);
		}

	}
	return true;
}
