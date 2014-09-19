package mapping.interfaces;

public interface Mapable {
	abstract  <F, T> void map(F from, T to);
	abstract  <F, T> T map(F from, Class<T> destinationType);
}
