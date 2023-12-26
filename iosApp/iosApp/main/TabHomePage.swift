import SwiftUI
import Shared

struct TabHomePage: View {
    
    private let component: TabHomeComponent
    

    init(_ component: TabHomeComponent) {
        self.component = component
    }
    
    var body: some View {
        VStack(alignment: .center, spacing: 8) {
            Text("TabHomePage")
        }
    }
}
