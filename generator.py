import random

# open file with users
input="users.txt"
spaced_users = open(input,"r")
# init userlist taken from file and the generated dictionary [user:{ list of user's friends }]
userlist = []
friends_map={}
# copy all users from disk to memory using var "userlist" and init the output struct in memory
users = spaced_users.read().split()
for user in users:
    userlist.append(user)
    friends_map[user]=[]
spaced_users.close()  # close input file
# Generate a random graph of users and their friends
print str(len(userlist))+"\n"
for user in userlist:
    numberoffriends=random.randint(1,len(userlist)/2-1)     # Pick a random number of friends for each user
    rsample=random.sample(userlist,numberoffriends)         # Get a random sample from userlist with size realted to the picked random number
    for friend in rsample:      # Add this sample from userlist to user's friends
        if friend!=user:        # Do not add user to user's friend!
            flag=False
            # Supposing every user has a UNIQUE name !
            for common in friends_map[user]:    # Do not add duplicated friends
                if common==friend:
                    flag=True
            if not flag:
                friends_map[user].append(friend)    # Add a friend to the user
            # Because "friend with" is an equivalence relation add user to the friend's list
            flag=False
            for common in friends_map[friend]:  # Do not add duplicated friends
                if common==user :
                    flag=True
            if not flag:
                friends_map[friend].append(user)    # Add user to friend's friends!
# open output file for writing
output="friends_graph.txt"
friends=open(output,"w")
#write output file in a format of [ user friend_A, friends_B, ..., friend_N ]
for user in friends_map.keys():
    friends.write(user+" ")
    for users_friend in friends_map[user]:
        friends.write(users_friend+", ")
    friends.seek(friends.tell()-2)
    friends.truncate()
    friends.write("\n")
friends.close()
