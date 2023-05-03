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

    public String getUrl(){ return url;}
    public String getDepth(){return depth;}

    public void incDepth(){ depth++; };

    @Override
    public boolean equals(Object o){
        URLDepthPair udp = (URLDepthPair)o;
        return udp.getUrl().equals(url);
    }

    public boolean fullMatch(Object o){
        URLDepthPair udp = (URLDepthPair)o;
        return (udp.getUrl().equals(url)&&udp.getDepth().equals(depth));
    }

    public String toString(){
        return url+" "+depth;
    }

    public String[] split(){ return url.split("/"); }

 }
