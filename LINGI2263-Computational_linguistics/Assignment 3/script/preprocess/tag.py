import os, platform, sys
"""
Lemmatize and tags with part-of-speech tags, usage is : 
python tag.py [[input file] treetagger executable]
"""


if platform.system() == "Windows":
    treetagger = "tag-english"
else:
    treetagger = "cmd/tree-tagger-english"


source_file_name = "definitions-6914.csv"
if(len(sys.argv)>1):
    source_file_name = sys.argv[1]
    
if(len(sys.argv)>2):
    treetagger = sys.argv[2]

def tag(source_file_name, treetagger):
    dest_file_name = "tagged_"+source_file_name

    tmp_file_name = "tmp"
    tmp_out_file_name = "tmp_out"

    source_file = open(source_file_name, 'r')

    dest = []

    i = 0
    for line in source_file:
        if line != "\n":
            i = i+1
            #get word and definition separately
            partline = line.partition(";")
            
            word = partline[0].strip()
            definition = partline[2].strip()
            
            #write definition to tmp file
            tmp_file = open(tmp_file_name, "w")
            tmp_file.write(definition)
            tmp_file.close()
            
            #execute treetagger on tmp file
            command = treetagger + " " + tmp_file_name + " " + tmp_out_file_name      
            if i%100==0:
                print(str(i)+" definitions tagged")
            os.system(command)
                
            #get tagged definition from file
            tagged_definition_list = []
            tmp_out_file = open(tmp_out_file_name, "r")
            for triplet in tmp_out_file:
                t = triplet.split("\t")
                tagged_definition_list.append("<"+t[1].strip()+">"+t[2].strip())
            tagged_definition = ' '.join(tagged_definition_list)
            tmp_out_file.close()
            
            #save results
            dest.append(word+"; "+tagged_definition)
            
    dest_file = open(dest_file_name, "w")
    dest_file.write('\n'.join(dest))
    dest_file.close()
    source_file.close()
    
tag(source_file_name, treetagger)