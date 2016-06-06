import random
input="users"
users = open(input,"r")
userlist = []
friends_map={}
for user in users:
    userlist.append(user)
    friends_map[user]=[]
users.close()
output="friends_graph"
friends=open(output,"w")
for user in userlist:
    numberoffriends=random.randint(1,5)
    rsample=random.sample(userlist,numberoffriends)
    for friend in rsample:
        if friend!=user:
            flag=False
            for common in friends_map[user]:
                if common==friend:
                    flag=True
            if not flag:
                friends_map[user].append(friend)
            flag=False
            for common in friends_map[friend]:
                if common==user :
                    flag=True
            if not flag:
                friends_map[friend].append(user)
#write output file
for user in friends_map.keys():
    friends.write(user+" ")
    for users_friend in friends_map[user]:
        friends.write(users_friend+" ")
    friends.write("\n")
friends.close()
