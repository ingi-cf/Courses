
from samplereader import SampleReader
from taggedsample import TaggedSample
from transcript import Transcript
import sys,os

tags        = ["sample_name","sample_type","gender","age","weight_kg","weight_pounds","height_cm","height_m","height_feet","bmi","temperature_faren","temperature_celsius","pulse","bf","blood_pressure","systolic_bp","diastolic_bp","oxygen"]
separator   = "\|\n"
scriptDir = os.path.abspath(os.path.join(sys.argv[0], os.path.pardir))

def execUnitex(src,unitexAppFolder):
    base =  os.path.abspath(src)[:-4]
    unitexToolLogger    = unitexAppFolder + "UnitexToolLogger"
    u_norm              = scriptDir + "/ressources/Norm.txt"
    u_alphabet          = scriptDir + "/ressources/Alphabet.txt"
    d_snt               = base + "_snt"
    f_snt               = base + ".snt"
    dst                 = base + ".tmp.txt"
    d_graph             = scriptDir + "/ressources/graphs"

    cmd = []
    cmd.append("\"" + unitexToolLogger + "\" Normalize \"" + src + "\" \"-r" + u_norm + "\"")
    cmd.append("mkdir \"" + d_snt + "\"")
    cmd.append("\"" + unitexToolLogger + "\" Tokenize \"" + f_snt + "\" \"-a" + u_alphabet + "\"")
    cmd.append("\"" + unitexToolLogger + "\" Grf2Fst2 \"" + d_graph + "/all.grf\" -y \"--alphabet=" + u_alphabet + "\"")
    cmd.append("\"" + unitexToolLogger + "\" Locate \"-t" + f_snt + "\" \"" + d_graph + "/all.fst2\" \"-a" + u_alphabet + "\" -L -M --all -b -Y")
    cmd.append("\"" + unitexToolLogger + "\" Concord \"" + d_snt + "/concord.ind\" \"-m" + dst + "\" ")
    
    for c in cmd:
        os.system(c)

    return dst

if len(sys.argv) < 3 :
    print("USAGE : python mt_extractor.py inputDir outputDir [pathToUnitexAppFolder]")
    exit()

inputDir = sys.argv[1]
outputDir = sys.argv[2]

if len(sys.argv) > 3:
    unitexPath = sys.argv[3] + "/"
else:
    unitexPath = "" 

for f in os.listdir(inputDir):
    src = inputDir + "/" + f
    dst = outputDir + "/" + f

    if(not os.path.isdir(src) and src[-4:] == ".txt"):
        tmpFile = execUnitex(src,unitexPath)

        tmpF = open(tmpFile,"r",encoding="utf-16")
        output = open(dst,"w",encoding="utf-16")
        sr = SampleReader()
        sr.setSeparator(separator)
        sr.setStream(tmpF)
        
        i=0

        for sample in sr.getSample():
            ts = TaggedSample(tags)
            ts.parse(sample)
            transcript = Transcript(ts)
            output.write(str(transcript)+"\n")
            i = i+1
            

        tmpF.close()
        output.close()
        #os.system("rm "+ tmpFile)
    else:
        print(src + " not taken in charge : this is not a .txt file\n")


