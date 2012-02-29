
from samplereader import SampleReader
from taggedsample import TaggedSample
from transcript import Transcript
import sys,os

tags        = ["sample_name","sample_type","gender","age","weight_kg","weight_pounds","height_cm","height_m","height_feet","bmi","temperature_faren","temperature_celsius","pulse","bf","blood_pressure","systolic_bp","diastolic_bp","oxygen"]
separator   = "\|\n"
tmpFile     = "/tmp/mt_extractor.txt"


if len(sys.argv) < 2 :
    print("USAGE : python mt_extractor.py inputDir outputDir [pathToUnitexAppFolder]")
    exit()

inputDir = sys.argv[1]
outputDir = sys.argv[2]

if len(sys.argv) > 3:
    unitexPath = sys.argv[2]
else:
    unitexPath = "" 

for f in os.listdir(inputDir):
    src = inputDir + "/" + f
    dst = outputDir + "/" + f
    if(not os.path.isdir(src)):
        tmpFile = src
        print(f+"\n")
        

        tmpF = open(tmpFile,"r",encoding="utf-16")
        output = open(dst,"w",encoding="utf-16")
        sr = SampleReader()
        sr.setSeparator(separator)
        sr.setStream(tmpF)
        
        i=0

        for sample in sr.getSample():
            print("sample :"+str(i))
            ts = TaggedSample(tags)
            ts.parse(sample)
            transcript = Transcript(ts)
            output.write(str(transcript)+"\n")
            i = i+1
            

        tmpF.close()
        output.close()

