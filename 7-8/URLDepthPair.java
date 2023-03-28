class URLDepthPair {
    String url;
    int depth;

    URLDepthPair(String url, int depth){
        this.url = url;
        this.depth = depth;
    }

    URLDepthPair(String url){
        this(url,0);
    }

    public void incDepth(){ depth++; };

    public String toString(){
        return url+" "+depth;
    }

    public String[] split(){ return url.split("/"); }

 }
