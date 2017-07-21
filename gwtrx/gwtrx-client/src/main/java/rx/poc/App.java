package rx.poc;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import rx.Single;

public class App implements EntryPoint {

	private class A{
		private int value;

		A(int value) {
			this.value = value;
		}
	}

	private class B{
		private String value;

		B(String value) {
			this.value = value;
		}
	}

	private class AB{
		private A a;
		private B b;

		AB(A a, B b) {
			this.a = a;
			this.b = b;
		}

		@Override
		public String toString() {
			return a.value+" : "+b.value;
		}
	}

	private Single<A> aSingle;
	private Single<B> bSingle;

	public void onModuleLoad() {
		final Button buttonA = new Button("Button1");
		buttonA.addClickHandler(event -> aSingle=Single.just(new A(19)));

		final Button buttonB = new Button("Button1");
		buttonB.addClickHandler(event -> bSingle=Single.just(new B("b something")));

		Single<AB> abSingle=Single.zip(aSingle,bSingle, AB::new);
		abSingle.subscribe(ab -> Window.alert(ab.toString()));

		RootPanel.get().add(buttonA);

		RootPanel.get().add(buttonB);
	}
}
