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
        if self.tSVal.get('sample_name'):
            self.s_name = self.tSVal.get('sample_name')[-1]
        if self.tSVal.get('sample_type'):
            self.s_type = self.tSVal.get('sample_type')[-1]
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
            
        #temperature
        if(self.tSVal.get('temperature_celsius')[-1]):
            self.values['temperature'] = self.tSVal.get('temperature_celsius')[-1]
        elif(self.tSVal.get('temperature_faren')[-1]):
            self.values['temperature'] = (float(self.tSVal.get('temperature_faren')[-1])-32)/1.8
        
        #pulse
        self.values['pulse'] = self.tSVal.get('pulse')[-1]
        
        #breathing frequency
        self.values['breathing frequency'] = self.tSVal.get('bf')[-1]
        
        #blood pressure
        if(self.tSVal.get('blood_pressure')[-1]):
            self.values['blood pressure'] = self.tSVal.get('blood_pressure')[-1]+"(diastolic/systolic)"
        elif(self.tSVal.get('systolic_bp')[-1]):
            self.values['blood pressure'] = "?/"+self.tSVal.get('systolic_bp')[-1]+"(diastolic/systolic)"
        elif(self.tSVal.get('diastolic_bp')[-1]):
            self.values['blood pressure'] = self.tSVal.get('diastolic_bp')[-1]+"/?"+"(diastolic/systolic)"
            
        #oxygen saturation
        self.values['oxygen saturation'] = self.tSVal.get('oxygen')[-1]+"%"
            
        
    
    def findVital(self):
        pass

    def __str__(self):
        ret = ""
        ret += 'sample name:' 
        if self.s_name:
            ret += self.s_name 
        ret+= ",\t"
        ret += 'sample type:' 
        if self.s_type:
            ret += self.s_type 
        ret+= ",\t"

        for (k,v) in self.units.items():
            if k in self.values and self.values[k]:
                ret += k + ":"
                ret += str(self.values[k])
                ret += str(v) 
                ret += ",\t"

        return ret[:-2]
