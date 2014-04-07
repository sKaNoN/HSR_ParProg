package testate.testat2.aufgabe03;

import java.util.concurrent.atomic.AtomicReference;

// TODO: Implement lock-free stack
public class LockFreeStack<T> implements Stack<T> {
	private AtomicReference<Node<T>> top = new AtomicReference<>();

	// TODO: Design stack node class
	public class Node<T> {
		private T value;
		private Node<T> next;

		public Node(T value) {
			this.value = value;
			
		}

		public T get() {
			return value;
		}

		public void setNext(Node<T> target) {
			next = target;
		}

		public Node<T> getNext() {
			return next;
		}

	}

	public LockFreeStack() {
		top.set(new Node<T>(null));
	}
	public void push(T value) {
		Node<T> newNode = new Node<>(value);
		Node<T> current;
		do {
			current = top.get();
			newNode.setNext(current);
		} while (!top.compareAndSet(current, newNode));
	}

	public T pop() {
		Node<T> current;
		do {
			current = top.get();
			if (current == null) return null;
		} while (!top.compareAndSet(current, current.getNext()));
		return current.get();
	}
}
