# android-exemplar-circuit

An Android app template pre-wired with the opinionated stack I use, so new projects can start at feature work instead of boilerplate.

The sample app fetches GitHub repositories and navigates to a detail screen — just enough to exercise each layer without distracting from the patterns.

---

## What's pre-wired

| Layer | Library | What you get |
|---|---|---|
| Navigation & architecture | [Circuit](https://slackhq.github.io/circuit/) | Screen/Presenter/UI pattern with code generation, retained state, and test utilities |
| UI | [Jetpack Compose](https://developer.android.com/compose) + Material3 | Compose BOM, theming, edge-to-edge, Compose previews |
| Dependency injection | [Hilt](https://dagger.dev/hilt/) | App/Activity entry points, singleton module, KSP codegen |
| Networking | [Retrofit](https://square.github.io/retrofit/) + OkHttp | Retrofit instance, Gson converter, logging interceptor |
| Testing | Circuit Test, Coroutines Test, AssertK | `Presenter.test()`, `FakeNavigator`, fake service pattern |

---

## Architecture

Each screen follows the Circuit Presenter/UI pattern with a `StateHolder` wrapper:

```
Screen (Parcelable nav key)
  └── Presenter — owns state logic, coroutines, navigation commands
        └── StateHolder<UiState, UiEvent> — passed to UI
              └── UI — stateless composable, fires events via onEvent
```

- **Screen**: a `@Parcelize data object/class` used as a navigation key
- **Presenter**: a `@Composable` function annotated with `@CircuitInject` that produces a `StateHolder`
- **UI**: a `@Composable` annotated with `@CircuitInject` that renders state and dispatches events
- **StateHolder**: a thin wrapper implementing `CircuitUiState` that pairs immutable state with an event callback

`@CircuitInject` + KSP codegen wires presenters and UIs into Hilt automatically — no manual factory registration.

---

## Project structure

```
app/src/main/java/com/nak5/exemplar/
├── di/           Hilt module (Circuit, Retrofit, NetworkService)
├── screens/      Circuit Screen definitions (nav keys)
├── presenters/   State logic and navigation
├── ui/           Stateless Compose UIs
├── uistate/      UiState, UiEvent, and StateHolder
└── network/      Retrofit service and data models
```

---

## Tech stack

| | Version |
|---|---|
| Kotlin | 2.3.20 |
| AGP | 9.1.0 |
| Circuit | 0.33.1 |
| Compose BOM | 2026.03.01 |
| Hilt | 2.59.2 |
| minSdk / targetSdk | 24 / 36 |

---

## Using as a template

1. Clone the repo
2. Rename the package (`com.nak5.exemplar` → your package) via Android Studio refactor
3. Delete the sample screens, presenters, UIs, and network layer
4. Add your first screen following the existing pattern
5. Build

---

## References

- [Circuit docs](https://slackhq.github.io/circuit/)
- [Circuit code generation](https://slackhq.github.io/circuit/code-gen/)
- [Circuit testing](https://slackhq.github.io/circuit/testing/)
- [Hilt docs](https://dagger.dev/hilt/)
- [Jetpack Compose docs](https://developer.android.com/compose)
