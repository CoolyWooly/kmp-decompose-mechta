import SwiftUI
import Shared

struct CheckoutPage: View {
    
    private let component: CheckoutComponent
    

    init(_ component: CheckoutComponent) {
        self.component = component
    }
    
    var body: some View {
        VStack(alignment: .center, spacing: 8) {
            Text("CheckoutPage")
        }
    }
}
