data <- Corpus( DirSource ("C:\\Users\\sundi133\\data\\keywords1"), readerControl = list(language = "en_US"))
myCorpus <- tm_map(data, tolower)

 myCorpus <- tm_map(myCorpus, removePunctuation)
 myCorpus <- tm_map(myCorpus, removeNumbers)
 myStopwords <- c(stopwords('english'), "available", "via")
 myCorpus <- tm_map(myCorpus, removeWords, myStopwords)
 
 myDtm <- TermDocumentMatrix(myCorpus, control = list(minWordLength = 1))
 termDocMatrix<-myDtm 
 termDocMatrix[5:10,1:20]
 
 termDocMatrix <- as.matrix(termDocMatrix)
 termDocMatrix[termDocMatrix>=1] <- 1
 termMatrix <- termDocMatrix %*% t(termDocMatrix)

 library(igraph)
 g <- graph.adjacency(termMatrix, weighted=T, mode = "undirected")
 g <- simplify(g)
 V(g)$label <- V(g)$name
 V(g)$degree <- degree(g)
 set.seed(3000)
 layout1 <- layout.fruchterman.reingold(g)
 tkplot(g, layout=layout1)