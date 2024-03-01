import SwiftUI
import Shared

@main
struct iOSApp: App {
    @UIApplicationDelegateAdaptor(AppDelegate.self)
    var appDelegate: AppDelegate
    
    init() {
        KoinKt.doInitKoin()
    }
    
	var body: some Scene {
		WindowGroup {
            RootPage(appDelegate.root)
		}
	}
}

class AppDelegate: NSObject, UIApplicationDelegate {
    private var stateKeeper = StateKeeperDispatcherKt.StateKeeperDispatcher(savedState: nil)

    lazy var root: RootComponent = RootComponent(
        componentContext: DefaultComponentContext(
            lifecycle: ApplicationLifecycle(),
            stateKeeper: stateKeeper,
            instanceKeeper: nil,
            backHandler: nil
        )
    )

    func application(_ application: UIApplication, shouldSaveSecureApplicationState coder: NSCoder) -> Bool {
        CodingKt.encodeParcelable(coder, value: stateKeeper.save(), key: "savedState")
        return true
    }
    
    func application(_ application: UIApplication, shouldRestoreSecureApplicationState coder: NSCoder) -> Bool {
        do {
            let savedState = try CodingKt.decodeParcelable(coder, key: "savedState") as! ParcelableParcelableContainer
            stateKeeper = StateKeeperDispatcherKt.StateKeeperDispatcher(savedState: savedState)
            return true
        } catch {
            return false
        }
    }
}
