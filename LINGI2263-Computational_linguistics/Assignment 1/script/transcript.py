import re

class Transcript:
    s_name = None
    s_type = None

    tSVal  = None

    units = {'gender':'', 'age':'', 'weight':'kg', 'height':'m', 'bmi':''
            ,'temperature':'°C', 'pulse':'BPM', 'breathing frequency':'breadth per minute','blood pressure':'mmHg','oxygen saturation':'%'}
    values = None

    def __init__(self,taggedSample):
        self.values={}
        self.tSVal = taggedSample
        s_name = self.tSVal.get('sample_name')
        s_type = self.tSVal.get('sample_type')
        self.findBasic()


    def findBasic(self):
        if(self.tSVal.get('gender')):
            self.values['gender'] = self.tSVal.get('gender')[-1]
        if(self.tSVal.get('age')):
            self.values['age'] = self.tSVal.get('age')[-1]

        #weight
        if(self.tSVal.get('weight_kg')):
            self.values['weight'] = self.tSVal.get('weight_kg')[-1]
        elif(self.tSVal.get('weight_pounds')):
            self.values['weight'] = float(self.tSVal.get('weight_pounds')[-1])*0.45359237

        #height
        if(self.tSVal.get('height_cm')):
            self.values['height'] = float(self.tSVal.get('height_cm')[-1]) / 100
            print("a" + str(self.values['height'])+"\n")
        elif(self.tSVal.get('height_m')):
            self.values['height'] = self.tSVal.get('height_m')[-1]
            print("b" + str(self.values['height'])+"\n")
        elif(self.tSVal.get('height_feet')):
            feet,inches = re.match("(.*)\'(.*)\"",self.tSVal.get('height_feet')[-1]).groups()
            self.values['height'] = float(feet)*0.3048 + float(inches)*0.0254
            print(feet + " - " +inches + "\n")
            print("c" + str(self.values['height'])+"\n")


        #bmi
        if(self.tSVal.get('bmi')):
            self.values['bmi'] = self.tSVal.get('bmi')[-1]
        elif('weight' in self.values and 'height' in self.values):
            print('h' + str(self.values['height'])+"\n")
            self.values['bmi'] = float(self.values.get('weight')) / (float(self.values.get('height')))**2
            


    
    def findVital(self):
        pass

    def __str__(self):
        ret = ""
        ret += 'sample name:' 
        if self.s_name:
            ret += s_name 
        ret += 'sample type:' 
        if self.s_type:
            ret += s_type 

        for (k,v) in self.units.items():
            ret += k + ":"
            if k in self.values and self.values[k]:
                ret += str(self.values[k])
                ret += str(v) 
            else:
                ret += "None"
            ret += ",\t"

        return ret[:-2]
