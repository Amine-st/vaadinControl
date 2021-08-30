package com.example.application.views.main;


        import java.util.ArrayList;
        import java.util.List;
        import java.util.Optional;

        import com.example.application.views.attendance.AttendanceView;
        import com.example.application.views.device.DeviceView;
        import com.example.application.views.deviceConnection.DeviceConnectionView;
        import com.example.application.views.sync.SyncView;
        import com.example.application.views.test.TestView;
        import com.example.application.views.timeZone.TimeZoneView;
        import com.example.application.views.user.UserView;
        import com.vaadin.flow.component.Component;
        import com.vaadin.flow.component.ComponentUtil;
        import com.vaadin.flow.component.Text;
        import com.vaadin.flow.component.applayout.AppLayout;
        import com.vaadin.flow.component.applayout.DrawerToggle;
        import com.vaadin.flow.component.button.Button;
        import com.vaadin.flow.component.html.Anchor;
        import com.vaadin.flow.component.html.Image;
        import com.vaadin.flow.component.html.Span;
        import com.vaadin.flow.component.html.H1;
        import com.vaadin.flow.component.orderedlayout.FlexComponent;
        import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
        import com.vaadin.flow.component.orderedlayout.VerticalLayout;
        import com.vaadin.flow.component.tabs.Tab;
        import com.vaadin.flow.component.tabs.Tabs;
        import com.vaadin.flow.component.tabs.TabsVariant;
        import com.vaadin.flow.router.RouterLink;
        import com.vaadin.flow.router.Route;
        import com.vaadin.flow.router.PageTitle;
        import com.vaadin.flow.server.PWA;
        import com.vaadin.flow.theme.Theme;
        import com.vaadin.flow.component.avatar.Avatar;

/**
 * The main view is a top-level placeholder for other views.
 */
@PWA(name = "My App", shortName = "My App", enableInstallPrompt = false)
@Theme(themeFolder = "myapp")
@PageTitle("Main")
public class MainView extends AppLayout {

    public static class MenuItemInfo {

        private String text;
        private String iconClass;
        private Class<? extends Component> view;

        public MenuItemInfo(String text, String iconClass, Class<? extends Component> view) {
            this.text = text;
            this.iconClass = iconClass;
            this.view = view;
        }

        public String getText() {
            return text;
        }

        public String getIconClass() {
            return iconClass;
        }

        public Class<? extends Component> getView() {
            return view;
        }

    }

    private final Tabs menu;
    private H1 viewTitle;

    public MainView() {
        setPrimarySection(Section.DRAWER);
        addToNavbar(true, createHeaderContent());
        menu = createMenu();
        addToDrawer(createDrawerContent(menu));
    }

    private Component createHeaderContent() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setClassName("sidemenu-header");
        layout.getThemeList().set("dark", true);
        layout.setWidthFull();
        layout.setSpacing(false);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.add(new DrawerToggle());
        viewTitle = new H1();
        layout.add(viewTitle);

        Avatar avatar = new Avatar();
        avatar.addClassNames("ms-auto", "me-m");
        layout.add(avatar);
        Anchor logout = new Anchor("logout", "Log out");
        layout.add(logout);
        return layout;
    }

    private Component createDrawerContent(Tabs menu) {
        VerticalLayout layout = new VerticalLayout();
        layout.setClassName("sidemenu-menu");
        layout.setSizeFull();
        layout.setPadding(false);
        layout.setSpacing(false);
        layout.getThemeList().set("spacing-s", true);
        layout.setAlignItems(FlexComponent.Alignment.STRETCH);
        HorizontalLayout logoLayout = new HorizontalLayout();
        logoLayout.setId("logo");
        logoLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        logoLayout.add(new Image("images/logo.png", "My App logo"));
        logoLayout.add(new H1("My App"));
        layout.add(logoLayout, menu);


        return layout;
    }

    private Tabs createMenu() {
        final Tabs tabs = new Tabs();
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        tabs.addThemeVariants(TabsVariant.LUMO_MINIMAL);
        tabs.setId("tabs");
        for (Tab menuTab : createMenuItems()) {
            tabs.add(menuTab);
        }
        return tabs;
    }

    private List<Tab> createMenuItems() {

        List<Tab> tabs = new ArrayList<>();

        tabs.add(createTab(new MenuItemInfo("Test", "la la-globe", TestView.class)));

            tabs.add(createTab(new MenuItemInfo("Connect to device", "la la-globe", DeviceConnectionView.class)));
            tabs.add(createTab(new MenuItemInfo("Attendance", "la la-globe", AttendanceView.class)));
            tabs.add(createTab(new MenuItemInfo("User", "la la-globe", UserView.class)));
            tabs.add(createTab(new MenuItemInfo("Time zone", "la la-globe", TimeZoneView.class)));
            tabs.add(createTab(new MenuItemInfo("Device info", "la la-globe", DeviceView.class)));
            tabs.add(createTab(new MenuItemInfo("Syncronisation", "la la-globe", SyncView.class)));


        return tabs;
    }

    private static Tab createTab(MenuItemInfo menuItemInfo) {
        Tab tab = new Tab();
        RouterLink link = new RouterLink();
        link.setRoute(menuItemInfo.getView());
        Span iconElement = new Span();
        iconElement.addClassNames("text-l", "pr-s");
        if (!menuItemInfo.getIconClass().isEmpty()) {
            iconElement.addClassNames(menuItemInfo.getIconClass());
        }
        link.add(iconElement, new Text(menuItemInfo.getText()));
        tab.add(link);
        ComponentUtil.setData(tab, Class.class, menuItemInfo.getView());
        return tab;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        getTabForComponent(getContent()).ifPresent(menu::setSelectedTab);
        viewTitle.setText(getCurrentPageTitle());
    }

    private Optional<Tab> getTabForComponent(Component component) {
        return menu.getChildren().filter(tab -> ComponentUtil.getData(tab, Class.class).equals(component.getClass()))
                .findFirst().map(Tab.class::cast);
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }
}