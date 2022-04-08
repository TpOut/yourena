## 三年级词汇表
# 日常用语：
daily = set()
daily = daily.union(("hello", "hi", "hey"))
daily = daily.union(("i", "me", "you", "us"))
daily = daily.union(("am", "is", "are"))
daily = daily.union(("my", "your"))
daily = daily.union(("mum","sure","brother","baby","boy"))
daily = daily.union(("bye", "good", "goodbye"))
daily = daily.union(("noon", "after", "afternoon", "morning"))
daily = daily.union(("who", "what", "where"))
daily = daily.union(("the", "it", "this", "here", "there","everywhere"))
daily = daily.union(("now","how","day","birthday","year","time","minute"))
daily = daily.union(("can","have","had","out"))
daily = daily.union(("a", "an", "and","so", "such","one"))
daily = daily.union(("some", "many"))
daily = daily.union(("match", "repeat","blank"))
daily = daily.union(("no", "do", "to", "too"))
daily = daily.union(("let","with","wait"))
daily = daily.union(("very", "nice", "great", "well","fine","funny","quiet","lovely","happy","surprise"))
daily = daily.union(("Miss", "Mr", "thank","please","welcome"))
daily = daily.union(("up", "down", "at","on","under","in"))
daily = daily.union(("ground", "around","away"))
daily = daily.union(("school","queen","story"))
daily = daily.union(("letter","sound","like"))
daily = daily.union(("gift","puppet","recycle","belong"))
daily = daily.union(("ice", "cream","hungry","wet"))
daily = daily.union(("start","first", "then","fill"))
daily = daily.union(("idea","aloud","song","cool","fat","big","long", "old","high","little"))
daily = daily.union(("ladder","log","trunck","jeep","milk","noodles","farm","bread","cake","juice"))
daily = daily.union(("water", "drink","picnic","plate","umbrella","water","candle"))

# 身体
body = set("body")
body = body.union(("shoulder", "knee", "toe", "leg", "nose"))

# 动作
act = set("act")
act = act.union("see", "look","read")
act = act.union("say", "chant","sing","kite","quack","talk","eat")
act = act.union("listen")
act = act.union("guess","find","tick","cross","learn")
act = act.union("play","turn","clap","wave","shake","cut","count")
act = act.union("make","meet","follow")
act = act.union("go","stamp","jump","dance")
act = act.union("open", "close","touch")
act = act.union("show", "check")
act = act.union("carry",)
act = act.union("stand","sit")

# 绘画
paint = set("paint")
paint = paint.union("colour")
paint = paint.union("red")
paint = paint.union("pink","purple")
paint = paint.union("circle")
paint = paint.union("rainbow")

# 数字
num = set("number")
num = num.union("one","two","three","four","five","six","seven","eight","nine","ten")

# 植物
plant = set()
plant = plant.union("apple","pear","carrot")
plant = plant.union("flower")

# 动物
animal = set()
animal = animal.union("vet","zoo")
animal = animal.union("ant","cat","dog","duck","snake","panda","elephant","bear","fox")
animal = animal.union("pig","tiger","bird","monkey","rabbit")

print(len(daily),len(body),len(act),len(paint),len(num),len(plant),len(animal))
