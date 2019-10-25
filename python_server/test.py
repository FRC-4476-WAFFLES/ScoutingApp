import pandas as pd
import numpy as np
import json
with open("LEGEND.json") as f:
    header = json.load(f)
df = pd.DataFrame(columns=header)
data = ['7735', '7', 'r', 'c', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '898', '9888', "Duncan.s"]
df = df.append(pd.DataFrame(data=[data], columns=header), ignore_index=True)
print(header)
print(data)

print("done")