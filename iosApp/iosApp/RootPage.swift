import SwiftUI
import Shared

struct RootPage: View {
    
    private let component: RootComponent
    
    @StateValue
    private var childStack: ChildStack<RootComponent.Configuration, RootComponent.Child>
    
    private var activeChild: RootComponent.Child { childStack.active.instance }
    
    init(_ component: RootComponent) {
        self.component = component
        _childStack = StateValue(component.childStack)
    }
    
    var body: some View {
            VStack {
                ChildView(child: activeChild)
                    .frame(maxHeight: .infinity)
            }
        }
}

private struct ChildView: View {
    let child: RootComponent.Child
    
    var body: some View {
        switch child {
        case let child as RootComponent.ChildSplashscreen: SplashscreenPage(child.component)
        case let child as RootComponent.ChildCheckout: CheckoutPage(child.component)
        case let child as RootComponent.ChildMain: MainPage(child.component)
        case let child as RootComponent.ChildOnBoarding: OnBoardingPage(child.component)
        default: EmptyView()
        }
    }
}
