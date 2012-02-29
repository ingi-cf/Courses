from samplereader import SampleReader

sr = SampleReader()
sr.setSeparator("\n\n\|\n\n\n")
sr.setStream(open("testFile.txt"))

for sample in sr.getSample():
    print("--\n"+ sample + "--\n")

