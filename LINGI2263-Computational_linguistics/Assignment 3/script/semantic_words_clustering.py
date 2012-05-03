import cluster, preprocess.tag, preprocess.filter, re, platform, sys

nbCLusters=1500
source_file_name="definitions-6914.csv"
output_file_name=""

stopwords = [",", ";", "a","able","about","across","after","all","almost","also","am","among","an","and","any","are","as","at","be","because","been","but","by","can","cannot","could","dear","did","do","does","either","else","ever","every","for","from","get","got","had","has","have","he","her","hers","him","his","how","however","i","if","in","into","is","it","its","just","least","let","like","likely","may","me","might","most","must","my","neither","no","nor","not","of","off","often","on","only","or","other","our","own","rather","said","say","says","she","should","since","so","some","than","that","the","their","them","then","there","these","they","this","tis","to","too","twas","us","wants","was","we","were","what","when","where","which","while","who","whom","why","will","with","would","yet","you","your"]
stopposes = ["DT", "SENT", ",", "IN", "CC"]

if platform.system() == "Windows":
    treetagger = "tag-english"
else:
    treetagger = "tree-tagger-english"
    
if len(sys.argv) < 3:
    print("This script takes minimum 2 arguments the definition file and the cluster file paths\n python semantic_words_clustering.py definitions_file clusters_file [k_value [treetagger_executabe]]")
    sys.exit(-1)

source_file_name          = sys.argv[1]
output_file_name     = sys.argv[2]


print("Starting : \n\tdef file \t : " + source_file_name + "\n\tclusters file\t : " + output_file_name + "\n")

if len(sys.argv) > 3:
    nbCLusters = int(sys.argv[3])
if len(sys.argv) > 4:
    treetagger = sys.argv[4]
        
if not re.match(".*tagged.*",source_file_name):
    preprocess.tag.tag(source_file_name, treetagger)
    if not re.match(".*filtered.*",source_file_name):
        preprocess.filter.filter(source_file_name+"_tagged", stopwords, stopposes)

        cluster.cluster(source_file_name+"_tagged_filtered",output_file_name,nbCLusters)
    else:
        cluster.cluster(source_file_name+"_tagged",output_file_name,nbCLusters)
else:
    if not re.match(".*filtered.*",source_file_name):
        preprocess.filter.filter(source_file_name, stopwords, stopposes)

        cluster.cluster(source_file_name+"_filtered",output_file_name,nbCLusters)
    else:
        cluster.cluster(source_file_name,output_file_name,nbCLusters)
