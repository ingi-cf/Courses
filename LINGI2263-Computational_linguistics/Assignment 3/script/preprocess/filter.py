import re, sys

source_file_name = "tagged_definitions-6914.csv"
if(len(sys.argv)>1):
    source_file_name = sys.argv[1]
    
stopwords = [",", ";", "a","able","about","across","after","all","almost","also","am","among","an","and","any","are","as","at","be","because","been","but","by","can","cannot","could","dear","did","do","does","either","else","ever","every","for","from","get","got","had","has","have","he","her","hers","him","his","how","however","i","if","in","into","is","it","its","just","least","let","like","likely","may","me","might","most","must","my","neither","no","nor","not","of","off","often","on","only","or","other","our","own","rather","said","say","says","she","should","since","so","some","than","that","the","their","them","then","there","these","they","this","tis","to","too","twas","us","wants","was","we","were","what","when","where","which","while","who","whom","why","will","with","would","yet","you","your"]
stopposes = ["DT", "SENT", ",", "IN", "CC"]

def filter(source_file_name, stopwords, stopposes):
    dest_file_name = "filtered_"+source_file_name
    dest = []
    
    source_file = open(source_file_name, 'r')
    
    for line in source_file:
        #get word and definition separately
        partline = line.partition(";")
        
        word = partline[0].strip()
        definition = partline[2].strip()
        
        #remove stop words
        for stopword in stopwords:
            definition = re.sub("<\w+>"+stopword+"(?= )","",definition).strip()
            
        #remove words with a stop POS
        for stoppos in stopposes:
            definition = re.sub("<"+stoppos+">\S+","",definition).strip()
            
        #save results
        dest.append(word+"; "+definition)
        
    dest_file = open(dest_file_name, "w")
    dest_file.write('\n'.join(dest))
    dest_file.close()
    source_file.close()
    
filter(source_file_name, stopwords, stopposes)