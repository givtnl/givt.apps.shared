import SwiftUI
import GivtCodeShare

struct ContentView: View {
	let greet = Greeting().greeting()
    let validator = CreditCardValidator()	
    
	var body: some View {
		Text(greet)
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
