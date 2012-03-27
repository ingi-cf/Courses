import re
import os
import glob
import sys

#get source files folder
input = "blogs"
output = "tokenized"
if(len(sys.argv)>1):
    input = sys.argv[1]
if(len(sys.argv)>2):
    output = sys.argv[2]
    
def tokenize(string):
    patterns = []
    patterns.append("[\w\-]+") #any word
    patterns.append("\'s|\'m") #is and am
    patterns.append("[:()@pPDdxXsS-_^\[\]]{2,3}") #smileys
    patterns.append("[0-9=\-*\/+%()^]{3,}") #mathematical exp or date
    patterns.append("[a-z._]+@[a-z.]+.[a-z]{2,3}") #mail adress
    patterns.append("\.(?= )|\.\n") #end of sentence
    patterns.append("(?<=\.) (?=[A-Z])") #new sentence
    return re.findall("|".join(patterns),string)



lexicon={}

#read all training files
for filename in glob.glob( os.path.join(input, '*train.txt')):
    file = open(filename, "r")

    tokenized_file = []
    #tokenize each line
    for line in file:
        tokens = tokenize(line)
        tokenized_file.append(tokens)
        for t in tokens:
            if(t in lexicon):
                lexicon[t] = lexicon[t] +1
            else:
                lexicon[t] = 1
    file.close()
    
    #write tokenized sentences to another file
    m = re.search("(?<="+input+").+",filename)
    outfilename = output+m.group()
    outfile = open(outfilename, "w")
    
    for line in tokenized_file:
        for tok in line:
            outfile.write(str(tok)+" ")
        outfile.write("\n")
    
    outfile.close()
    
    

#obtain 20 most used tokens
lexicon_sorted = sorted(lexicon.items(), key=lambda pair:pair[1], reverse=True)

print("lexicon size : "+str(len(lexicon_sorted)) + " tokens")
print("top 20 tokens : ")
for lex in lexicon_sorted[0:20]:
    print(str(lex[0]) + "  :  " +str(lex[1]))

outfile = open(output+"/lexicon.txt", "w")
for tok in lexicon_sorted:
    outfile.write(str(tok[0])+"\n")
outfile.close()