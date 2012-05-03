import math, time

def tfidfAddTo(a,b):
    """
    sum a and b and store it in a
    """
    for w in b.keys():
        if w not in a:
            a[w] = 0
        a[w] += b[w]
    return a

def sim(a,b):
    """
    compute the similitude between 2 unit-length tf-idf vector
    """
    s = 0
    for w in a:
        if w in b:
            s += a[w]*b[w]
    return s

def assign(cv,x):
    """
    assign words to a cluster
    
    PARAMS
    ------
    cv: list of the centroid vectors represented by dictionaries
    x : a dictionary where the defined word is the key and the value is another dictionary representing the tf-idf unit-length vector
    """
    y = {}
    log_w = 0
    for w in x:
        if(log_w%1000==0):
            print("assign " +str(log_w) )
        log_w +=1
        argmax      = None
        argmaxval   = None
        for i in range(len(cv)):
            s = sim(x[w],cv[i])
            if(argmax == None or argmaxval < s):
                argmax = i
                argmaxval = s
        y[w] = argmax
    return y




def centroidEstimation(y,k,x):
    cv = [None] * k
    for i in range(k):
        cv[i] = {}
    
    count = [0] * k

    for w in x:
        i = y[w]
        tfidfAddTo(cv[i],x[w])
        count[i] += 1

    for i in range(k):
        for w in cv[i]:
            cv[i][w] = cv[i][w] / count[i]
    return cv

def skmean(x,k):
    """
    perform the spherical k-mean for definitions
    
    PARAMS
    ------
    x : a dictionary where the defined word is the key and the value is another dictionary representing the tf-idf unit-length vector
    k : the number of clusters
    """

    #cv are the centroids vectors
    cv = [None] * k
    cv = list(x.values())[0:k]
    y = []
    y_old = [1]
    it = 0
    while y_old != y:
        print('iteration '+ str(it))
        it+=1
        print("assign start")
        y_old = dict(y)
        start = time.time()
        y = assign(cv,x)
        elapsed = (time.time() - start)
        print("time elapsed to assign : " + str( elapsed))
        print("estimation start")
        cv = centroidEstimation(y,k,x)
    clusters = [None] * k
    for i in range(k):
        clusters[i] = list(l for l, v in y.items() if v == i) 
    return clusters
