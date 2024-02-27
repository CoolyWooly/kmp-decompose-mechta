import SwiftUI
import Shared

struct MainPage: View {
    
    private let component: MainComponent
    
    @StateValue
    private var childStack: ChildStack<MainComponent.Configuration, MainComponent.Child>
    
    private var activeChild: MainComponent.Child { childStack.active.instance }


    init(_ component: MainComponent) {
        self.component = component
        _childStack = StateValue(component.childStack)
    }
    
    var body: some View {
            VStack {
                ChildView(child: activeChild)
                    .frame(maxHeight: .infinity)
                
                HStack(alignment: .bottom, spacing: 16) {
                    Button(action: component.onTabHomeClick) {
                        Label("Главная", systemImage: "123.rectangle")
                            .labelStyle(VerticalLabelStyle())
                            .opacity(activeChild is MainComponent.ChildTabHome ? 1 : 0.5)
                    }
                    
                    Button(action: component.onTabCatalogClick) {
                        Label("Каталог", systemImage: "list.bullet")
                            .labelStyle(VerticalLabelStyle())
                            .opacity(activeChild is MainComponent.ChildTabCatalog ? 1 : 0.5)
                    }

                    Button(action: component.onTabCartClick) {
                        Label("Корзина", systemImage: "location.north")
                            .labelStyle(VerticalLabelStyle())
                            .opacity(activeChild is MainComponent.ChildTabCart ? 1 : 0.5)
                    }

                    Button(action: component.onTabBonusClick) {
                        Label("Бонусы", systemImage: "location.north")
                            .labelStyle(VerticalLabelStyle())
                            .opacity(activeChild is MainComponent.ChildTabBonus ? 1 : 0.5)
                    }

                    Button(action: component.onTabProfileClick) {
                        Label("Профиль", systemImage: "location.north")
                            .labelStyle(VerticalLabelStyle())
                            .opacity(activeChild is MainComponent.ChildTabProfile ? 1 : 0.5)
                    }
                    }
                }
            }
        }
        


        
private struct ChildView: View {
    let child: MainComponent.Child
    
    var body: some View {
        switch child {
        case let child as MainComponent.ChildTabHome: TabHomePage(child.component)
        case let child as MainComponent.ChildTabCatalog: TabCatalogPage(child.component)
        case let child as MainComponent.ChildTabCart: TabCartPage(child.component)
        case let child as MainComponent.ChildTabBonus: TabBonusPage(child.component)
        case let child as MainComponent.ChildTabProfile: TabProfilePage(child.component)
        default: EmptyView()
        }
    }
}

private struct VerticalLabelStyle: LabelStyle {
    func makeBody(configuration: Configuration) -> some View {
        VStack(alignment: .center, spacing: 8) {
            configuration.icon
            configuration.title
        }
    }
}
