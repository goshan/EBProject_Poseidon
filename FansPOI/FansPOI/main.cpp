#include "FuncTools.h"
#include <iostream>
#include <string>
#include <locale>
using namespace std;



int main(int argc, char *argv[]){
	locale ::global(locale(""));
	
	if (argc < 3){
		cout <<"num of parameters error" <<endl;
		return 0;
	}

	string fans_dir(argv[1]);
	string res_dir(argv[2]);
	
	extract(fans_dir, res_dir);
	
	return 0;
}