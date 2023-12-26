import SwiftUI
import Shared

struct TabCartPage: View {
    
    private let component: TabCartComponent
    

    init(_ component: TabCartComponent) {
        self.component = component
    }
    
    var body: some View {
        VStack(alignment: .center, spacing: 8) {
            Text("TabCartPage")
        }
    }
}
