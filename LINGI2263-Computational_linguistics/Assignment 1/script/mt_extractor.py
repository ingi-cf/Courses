
from samplereader import SampleReader
from taggedsample import TaggedSample
from transcript import Transcript
import sys,os,shutil, platform
import shlex,subprocess

tags        = ["sample_name","sample_type","gender","age","weight_kg","weight_pounds","height_cm","height_m","height_feet","bmi","temperature_faren","temperature_celsius","pulse","bf","blood_pressure","systolic_bp","diastolic_bp","oxygen"]
separator   = "\|\n"
scriptDir = os.path.abspath(os.path.join(sys.argv[0], os.path.pardir))

def execUnitex(src,unitexAppFolder):
    base =  os.path.abspath(src)[:-4]
    ressources = os.path.join(scriptDir,'ressources')
    unitexToolLogger    = os.path.join(unitexAppFolder,"UnitexToolLogger")
    u_norm              = os.path.join(ressources, "Norm.txt")
    u_alphabet          = os.path.join(ressources, "Alphabet.txt")
    d_snt               = base + "_snt"
    f_snt               = base + ".snt"
    dst                 = base + ".tmp.txt"
    d_graph             = os.path.join(ressources, "graphs")

    u_concord           = os.path.join(d_snt, 'concord.ind')
    u_graph             = os.path.join(d_graph,'all.grf')
    u_graph2            = os.path.join(d_graph,'all.fst2')

    cmd = []
    cmd.append("\"" + unitexToolLogger + "\" Normalize \"" + src + "\" \"-r" + u_norm + "\"")
    os.mkdir(d_snt)
    cmd.append("\"" + unitexToolLogger + "\" Tokenize \"" + f_snt + "\" \"-a" + u_alphabet + "\"")
    cmd.append("\"" + unitexToolLogger + "\" Grf2Fst2 \"" + u_graph + "\" -y \"--alphabet=" + u_alphabet + "\"")
    cmd.append("\"" + unitexToolLogger + "\" Locate \"-t" + f_snt + "\" \"" + u_graph2 + "\" \"-a" + u_alphabet + "\" -L -M --all -b -Y")
    cmd.append("\"" + unitexToolLogger + "\" Concord \"" + u_concord + "\" \"-m" + dst + "\" ")
    
    for c in cmd:
        if platform.system() == "Windows":
            subprocess.call(c)
        else:
            os.system(c)
        print(c)
        #args = shlex.split(c)
        #subprocess.Popen(args)
        
        
    shutil.rmtree(d_snt)
    os.remove(f_snt)
    os.remove(u_graph2)

    return dst

if len(sys.argv) < 3 :
    print("USAGE : python mt_extractor.py inputDir outputDir [pathToUnitexAppFolder]")
    exit()

inputDir = sys.argv[1]
outputDir = sys.argv[2]

if len(sys.argv) > 3:
    unitexPath = sys.argv[3]
else:
    unitexPath = "" 

for f in os.listdir(inputDir):
    src = os.path.join(inputDir, f)
    dst = os.path.join(outputDir , f)

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
        #os.remove(tmpFile)
    else:
        print(src + " not taken in charge : this is not a .txt file\n")


