package testate.testat2.aufgabe03;

public class SynchronizedStack<T> implements Stack<T> {
	private java.util.Stack<T> stack = new java.util.Stack<>();
	
	@Override
	public void push(T item) {
		stack.push(item);
	}

	@Override
	public T pop() {
		return stack.pop();
	}
}
