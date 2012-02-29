from taggedsample import TaggedSample

ts = TaggedSample(["test"])
ts.parse("ceci est un {test}essai{/test} assez facile \n enfin {test}j'espère{/test}")
print(ts.summary())
