#include "FuncTools.h"
#include <iostream>
#include <string>
#include <locale>
using namespace std;



int main(int argc, char *argv[]){
	locale ::global(locale(""));
	
	if (argc < 3){
		cout <<"num of parameters error!" <<endl;
		return 0;
	}

	string file(argv[1]);
	string res_dir(argv[2]);

	extract(file, res_dir);
	
	return 0;
}