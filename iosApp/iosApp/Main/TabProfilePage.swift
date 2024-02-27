import SwiftUI
import Shared

struct TabProfilePage: View {
    
    private let component: TabProfileComponent
    

    init(_ component: TabProfileComponent) {
        self.component = component
    }
    
    var body: some View {
        VStack(alignment: .center, spacing: 8) {
            Text("TabProfilePage")
        }
    }
}
