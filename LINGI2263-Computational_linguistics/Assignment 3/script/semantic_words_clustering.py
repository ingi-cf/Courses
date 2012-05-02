import sys
from dictionary import Dictionary
from definition import Definition

if len(sys.argv) < 3:
    print("This script takes 2 arguments the definition file and the cluster file paths\n")
    sys.exit(-1)

p_def_file          = sys.argv[1]
p_clusters_file     = sys.argv[2]

print("Starting : \n\tdef file \t : " + p_def_file + "\n\tclusters file\t : " + p_clusters_file + "\n")

dico = Dictionary()

#Building the dictionary
def_file = open(p_def_file,'r',encoding='latin-1')

for l in def_file:
    if l != "\n":
        splitted = l.split(';\t')
        word        = splitted[0]
        dText       = ' '.join(splitted[1:])
        tmpDef      = Definition(word,dText)
        dico.insert(tmpDef)

def_file.close()


#compute
for d in dico.getDefs().values():
    d.computeTFIDFV(dico.getIDF())


