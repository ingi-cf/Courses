import sys
from dictionary import Dictionary
from definition import Definition
import skmean


def cluster(p_def_file, p_clusters_file, nbCLusters):
    dico = Dictionary()

#Building the dictionary
    def_file = open(p_def_file,'r',encoding='latin-1')

    for l in def_file:
        if l != "\n":
            splitted = l.split('; ')
            word        = splitted[0]
            dText       = ' '.join(splitted[1:])
            tmpDef      = Definition(word,dText)
            dico.insert(tmpDef)

    def_file.close()


#compute
    for d in dico.getDefs().values():
        d.computeTFIDFV(dico.getIDF())

    clusters = skmean.skmean(dico.getAllTFIDFV(),nbCLusters)

    f = open(p_clusters_file,'w')
    for i in range(len(clusters)):
        f.write(str(i))
        for w in clusters[i]:
            f.write(';'+w)
        f.write(',\n')
    f.close()
    


if __name__ == "__main__":

    if len(sys.argv) < 3:
        print("This script takes 2 arguments the definition file and the cluster file paths\n")
        sys.exit(-1)

    p_def_file          = sys.argv[1]
    p_clusters_file     = sys.argv[2]

    nbCLusters = 1000

    print("Starting : \n\tdef file \t : " + p_def_file + "\n\tclusters file\t : " + p_clusters_file + "\n")
    if len(sys.argv) > 3:
        nbCLusters = int(sys.argv[3])

    cluster(p_def_file,p_clusters_file,nbCLusters)
