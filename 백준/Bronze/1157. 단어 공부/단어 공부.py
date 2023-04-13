word = input().lower()
word_list = list(set(word))
count = []
for i in word_list:
    count.append(word.count(i))

max_num = max(count)
if max_num >= 2 and count.count(max_num) >= 2:
    print("?")
else:
    print(word_list[count.index(max_num)].upper())