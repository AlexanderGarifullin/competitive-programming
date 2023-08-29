void stressGenerate(){
    // add new
    // ofstream testFile("test.txt". ios::app);
    // replace new
    ofstream testFile("test.txt");

    ll seed = chrono::steady_clock::now().time_since_epoch().count();
    // int seed = 1;
    
    mt19937 rnd(seed);
   
    testFile << "SEED = " << seed << en;

    int n = rnd()% 10;
    for (int i = 0; i < n; ++i) {
        testFile << i << en;
    }

    testFile.close();
}


void stupid(){
    ifstream testFile("test.txt");
    ofstream stupidFile("stupid.txt");
    str seed;
    getline(testFile, seed);
    stupidFile << seed << en;

    str s;
    while(getline(testFile, s)) {
        stupidFile << s + "X" + s << en;
    }

    testFile.close();
    stupidFile.close();
}

void smart(){
    ifstream testFile("test.txt");
    ofstream smartFile("smart.txt");
    str seed;
    getline(testFile, seed);
    smartFile << seed << en;

    str s;
    while(getline(testFile, s)) {
        smartFile << "@"+  s  << en;
    }

    testFile.close();
    smartFile.close();
}


void checker(){
    ifstream smartFile("smart.txt");
    ifstream stupidFile("stupid.txt");
    ofstream resultFile("results.txt");

    string smartLine, stupidLine;
    int testNumber = 1;

    while (std::getline(smartFile, smartLine) && std::getline(stupidFile, stupidLine)) {
        resultFile << smartLine << ' ' << stupidLine << en;
        resultFile << testNumber << en;
        testNumber++;
    }

    smartFile.close();
    stupidFile.close();
    resultFile.close();
}
