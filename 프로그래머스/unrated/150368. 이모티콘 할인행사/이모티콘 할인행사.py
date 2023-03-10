from itertools import product

def solution(users, emoticons):
    
    answer = [0,0]
    
    for discounts in product((40, 30, 20, 10), repeat=len(emoticons)):
        sold = [0, 0]
        
        for user_discount, user_money in users:
            sold_emoticons = 0
            for emoticon, discount in zip(emoticons, discounts):
                if discount >= user_discount:
                    sold_emoticons += emoticon * (1 - discount / 100)
            if sold_emoticons >= user_money:
                sold[0] += 1
            else:
                sold[1] += sold_emoticons
        answer = max(answer, sold)

    return answer