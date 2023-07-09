package game;

public class Pair<X, Y> {
    private final X m_first;
    private final Y m_second;

    public Pair(X first, Y second) {
        m_first = first;
        m_second = second;
    }

    public X first() {
        return m_first;
    }

    public Y second() {
        return m_second;
    }
    
    public boolean equals(Pair<X, Y> other)
    {
    	return (this.first() == other.first()) && (this.second() == other.second());
    }
    
    public void show() {
    	System.out.println("Pair("+this.first()+","+this.second()+")");
    }
}