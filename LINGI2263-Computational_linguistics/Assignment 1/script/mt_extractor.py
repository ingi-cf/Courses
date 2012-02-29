
from samplereader import SampleReader
from taggedsample import TaggedSample
from transcript import Transcript
import sys

tags        = ["sample_name","sample_type","gender","age","weight_kg","weight_pounds","height_cm","height_m","height_feet","bmi","temperature_faren","temperature_celsius","pulse","bf","blood_pressure","systolic_bp","diastolic_bp","oxygen"]
separator   = "\|\n"


resultFile = sys.argv[1]
print("restult file :" + resultFile + "\n")
f = open(resultFile,"r",encoding="utf-16")
sr = SampleReader()
sr.setSeparator(separator)
sr.setStream(f)
i=0

for sample in sr.getSample():
    print("sample :"+str(i))
    ts = TaggedSample(tags)
    ts.parse(sample)
    transcript = Transcript(ts)
    print(str(transcript))
    i = i+1
    

f.close()
