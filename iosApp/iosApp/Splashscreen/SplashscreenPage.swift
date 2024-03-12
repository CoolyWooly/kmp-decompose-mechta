import SwiftUI
import Shared

struct SplashscreenPage: View {
    
    private let component: SplashscreenComponent
    

    init(_ component: SplashscreenComponent) {
        self.component = component
    }
    
    var body: some View {
        VStack(alignment: .center, spacing: 8) {
            Text("SplashscreenPage")
        }
    }
}
