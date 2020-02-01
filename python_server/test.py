import pandas as pd
import pandastable
import tkinter as tk
root = tk.Tk()
df = pd.read_csv("qr_out.csv")
print(df)


last_match_df = df.tail(6)
# validate.findMatch("qm19")
# msg = validate.findErrors(last_match_df)
# pt = pandastable.Table(root)
f = tk.Frame(root)
f.pack(side=tk.BOTTOM, fill=tk.BOTH, expand=1)
pt = pandastable.Table(f, dataframe=df, showtoolbar=True, showstatusbar=True)
pt.show()
otherstuff = tk.Frame(root)
otherstuff.pack(side=tk.TOP)
thing = tk.Label(otherstuff, text='Changes Needed:')
thing.pack(side=tk.TOP)
root.mainloop()
print(pt.model.df)
